import { TeachersResponse } from './../../models/msg/TeachersResponse';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { UserServiceService } from '../../services/user-service.service';
import { UtilsService } from '../../services/utils.service';
import { GlobalConfig } from 'src/app/models/GlobalConfig';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Profile } from 'src/app/models/Profile';
import { LegalIdType } from 'src/app/models/LegalIdType';
import { Country } from 'src/app/models/Country';
import { KernelServiceService } from '../../services/kernel-service.service';
import { University } from 'src/app/models/University';
import { AdminServiceService } from '../../services/admin-service.service';
import { SpinnerService } from '../../services/spinner.service';
import { Teacher } from 'src/app/models/Teacher';
import { Filter } from 'src/app/models/Filter';
import { Department } from 'src/app/models/Department';
import { Establishment } from 'src/app/models/Establishment';

@Component({
  selector: 'app-teachers-management',
  templateUrl: './teachers-management.component.html',
  styleUrls: ['./teachers-management.component.scss'],
})
export class TeachersManagementComponent  implements OnInit {

  teacherForm             : FormGroup;
  filterForm              : FormGroup;
  pageSize                : number = 10;
  page                    : number = 0;
  global                  : GlobalConfig;
  countries               : Country[];
  profiles                : Profile[];
  legalIdTypes            : LegalIdType[];
  establishments          : Establishment[];
  departments             : Department[];
  selectedEstablishment   : Establishment;
  university              : University;
  selectedCountry         : Country;
  teachersResponse        : TeachersResponse;
  showForm                : boolean = false;
  showFilterForm          : boolean = false;
  filter                  : Filter = new Filter();
  defaultProfile          : Profile | undefined;

  constructor(
    private utilsService            : UtilsService,
    private modalCtrl               : ModalController,
    private userServiceService      : UserServiceService,
    private formBuilder             : FormBuilder,
    private kernelServiceService    : KernelServiceService,
    private adminServiceService     : AdminServiceService,
    private spinnerService          : SpinnerService,
  ) {}

  async ngOnInit() {
    this.teacherForm = this.formBuilder.group({
      gender: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      thumbnail: ['', Validators.required],
      birthDate: ['', Validators.required],
      legalIdNumber: ['', Validators.required],
      legalIdType: ['', Validators.required],
      citizenship: ['', Validators.required],
      profile: ['', Validators.required],
      city: ['', Validators.required],
      establishment: [''],
      department: ['', Validators.required],
    });

    this.filterForm = this.formBuilder.group({
      page: [this.page],
      pageSize: [this.pageSize],
      firstName: [this.filter.firstName],
      lastName: [this.filter.lastName],
      username: [this.filter.username],
      legalIdNumber: [this.filter.legalIdNumber]
    });

    this.countries = await this.kernelServiceService.countriesGet();
    this.legalIdTypes = await this.kernelServiceService.legalIdTypesGet();
    this.profiles = await this.kernelServiceService.profilesGet();
    this.university = await this.kernelServiceService.universityGet();

    this.defaultProfile = this.profiles.find(profile => profile.code === 'teacher');
    this.teacherForm.patchValue({
      profile: this.defaultProfile
    });

    this.establishments = await this.kernelServiceService.establishmentsGet();

    this.teacherForm.get('citizenship')?.valueChanges.subscribe((selectedCountry: Country) => {
      this.selectedCountry = selectedCountry;
    });

    this.teacherForm.get('establishment')?.valueChanges.subscribe(async (selectedEstablishment: Establishment) => {
      this.selectedEstablishment = selectedEstablishment;
      this.departments = await this.kernelServiceService.departmentByEstablishmentCodeGet(selectedEstablishment.code);
    });

    this.filter.page = this.page;
    this.filter.pageSize = this.pageSize;
    this.teachersResponse = await this.adminServiceService.getTeachers(this.filter);
  }

  async submitTeacherForm() {
    if (this.teacherForm.valid) {
      this.adminServiceService.createTeacher(this.teacherForm.value);
      this.clearForm();
    } else {
      console.log('Form is not valid');
    }
  }

  async submitFilterForm() {
    if (this.filterForm.valid) {
      this.filter = this.filterForm.value;
      this.teachersResponse = await this.adminServiceService.getTeachers(this.filter);
    } else {
      console.log('Form is not valid');
    }
  }

  handleFileSelected(fileBase64 : string) {
    this.teacherForm.patchValue({
      thumbnail: fileBase64
    });
  }

  toggleForm() {
    this.showForm = !this.showForm;
  }

  toggleFilterForm() {
    this.showFilterForm = !this.showFilterForm;
  }

  async paginate(action: string) {
    if (action === 'next' && ! this.teachersResponse.last) {
      this.filter.page = this.teachersResponse.number+1;
    } else if (action === 'prev' && ! this.teachersResponse.first) {
      this.filter.page = this.teachersResponse.number-1;
    }
    if ((action === 'next' && ! this.teachersResponse.last) 
      || (action === 'prev' && ! this.teachersResponse.first)) {
        this.filter.pageSize = this.pageSize;
        this.teachersResponse = await this.adminServiceService.getTeachers(this.filter);
    }
  }

  async clearFilter() {
    this.filterForm.reset({
      page: this.page,
      pageSize: this.pageSize,
      firstName: null,
      lastName: null,
      apogeeCode: null,
      studentNationalCode: null
    });
    this.showFilterForm = false;
    this.filter = this.filterForm.value;
    this.teachersResponse = await this.adminServiceService.getTeachers(this.filterForm.value);
  }

  async refresh() {
    this.teachersResponse = await this.adminServiceService.getTeachers(this.filter);
  }

  async clearForm() {
    this.teacherForm = this.formBuilder.group({
      gender: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      thumbnail: ['', Validators.required],
      birthDate: ['', Validators.required],
      legalIdNumber: ['', Validators.required],
      legalIdType: ['', Validators.required],
      citizenship: ['', Validators.required],
      profile: ['', Validators.required],
      city: ['', Validators.required],
    });

    this.defaultProfile = this.profiles.find(profile => profile.code === 'teacher');
    this.teacherForm.patchValue({
      profile: this.defaultProfile
    });
  }
}
