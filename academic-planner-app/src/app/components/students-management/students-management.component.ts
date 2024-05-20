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
import { Student } from 'src/app/models/Student';
import { Filter } from 'src/app/models/Filter';
import { StudentsResponse } from 'src/app/models/msg/StudentsResponse';

@Component({
  selector: 'app-students-management',
  templateUrl: './students-management.component.html',
  styleUrls: ['./students-management.component.scss'],
})
export class StudentsManagementComponent  implements OnInit {

  studentForm     : FormGroup;
  filterForm      : FormGroup;
  pageSize        : number = 1;
  page            : number = 0;
  global          : GlobalConfig;
  studentsResponse: StudentsResponse;
  countries       : Country[];
  profiles        : Profile[];
  legalIdTypes    : LegalIdType[];
  university      : University;
  selectedCountry : Country;
  showForm        : boolean = false;
  showFilterForm  : boolean = false;
  filter          : Filter = new Filter();
  defaultProfile  : Profile | undefined;

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
    this.studentForm = this.formBuilder.group({
      // id: [''],
      // username: ['', Validators.required],
      // password: ['', [Validators.required, Validators.minLength(6)]],
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
      apogeeCode: ['', Validators.required],
      studentNationalCode: ['', Validators.required]
    });

    this.filterForm = this.formBuilder.group({
      page: [this.page],
      pageSize: [this.pageSize],
      firstName: [this.filter.firstName],
      lastName: [this.filter.lastName],
      apogeeCode: [this.filter.apogeeCode],
      studentNationalCode: [this.filter.studentNationalCode],
      // username: ['', Validators.required],
      // legalIdNumber: ['', Validators.required]
    });


    this.countries = await this.kernelServiceService.countriesGet();
    this.legalIdTypes = await this.kernelServiceService.legalIdTypesGet();
    this.profiles = await this.kernelServiceService.profilesGet();
    this.university = await this.kernelServiceService.universityGet();

    this.defaultProfile = this.profiles.find(profile => profile.code === 'student');
    this.studentForm.patchValue({
      profile: this.defaultProfile
    });

    this.studentForm.get('citizenship')?.valueChanges.subscribe((selectedCountry: Country) => {
      this.selectedCountry = selectedCountry;
    });
    
    this.filter.page = this.page;
    this.filter.pageSize = this.pageSize;
    this.studentsResponse = await this.adminServiceService.getStudents(this.filterForm.value);
  }

  async submitStudentForm() {
    if (this.studentForm.valid) {
      this.adminServiceService.createStudent(this.studentForm.value);
      this.clearForm();
    } else {
      console.log('Form is not valid');
    }
  }

  async submitFilterForm() {
    if (this.filterForm.valid) {
      this.filter = this.filterForm.value;
      this.studentsResponse = await this.adminServiceService.getStudents(this.filter);
    } else {
      console.log('Form is not valid');
    }
  }

  handleFileSelected(fileBase64 : string) {
    this.studentForm.patchValue({
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
    if (action === 'next' && ! this.studentsResponse.last) {
      this.filter.page = this.studentsResponse.number+1;
    } else if (action === 'prev' && ! this.studentsResponse.first) {
      this.filter.page = this.studentsResponse.number-1;
    }
    if ((action === 'next' && ! this.studentsResponse.last) 
      || (action === 'prev' && ! this.studentsResponse.first)) {
        this.filter.pageSize = this.pageSize;
        this.studentsResponse = await this.adminServiceService.getStudents(this.filter);
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
    this.studentsResponse = await this.adminServiceService.getStudents(this.filterForm.value);
  }

  async refresh() {
    this.studentsResponse = await this.adminServiceService.getStudents(this.filter);
  }

  async clearForm() {
    this.studentForm = this.formBuilder.group({
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
      apogeeCode: ['', Validators.required],
      studentNationalCode: ['', Validators.required]
    });

    this.defaultProfile = this.profiles.find(profile => profile.code === 'student');
    this.studentForm.patchValue({
      profile: this.defaultProfile
    });

  }

}
