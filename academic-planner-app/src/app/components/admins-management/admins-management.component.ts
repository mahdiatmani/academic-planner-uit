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
import { Admin } from 'src/app/models/Admin';
import { PersonsResponse } from 'src/app/models/msg/PersonsResponse';
import { Filter } from 'src/app/models/Filter';

@Component({
  selector: 'app-admins-management',
  templateUrl: './admins-management.component.html',
  styleUrls: ['./admins-management.component.scss'],
})
export class AdminsManagementComponent  implements OnInit {

  adminForm       : FormGroup;
  filterForm      : FormGroup;
  pageSize        : number = 10;
  page            : number = 0;
  global          : GlobalConfig;
  countries       : Country[];
  profiles        : Profile[];
  legalIdTypes    : LegalIdType[];
  university      : University;
  selectedCountry : Country;
  personsResponse : PersonsResponse;
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
    this.adminForm = this.formBuilder.group({
      gender: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      thumbnail: ['', Validators.required],
      birthDate: ['', Validators.required],
      legalIdNumber: ['', Validators.required],
      legalIdType: ['', Validators.required],
      citizenship: ['', Validators.required],
      city: ['', Validators.required],
      profile: ['', Validators.required]
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

    this.defaultProfile = this.profiles.find(profile => profile.code === 'admin');
    console.log(this.defaultProfile);
    this.adminForm.patchValue({
      profile: this.defaultProfile
    });

    this.adminForm.get('citizenship')?.valueChanges.subscribe((selectedCountry: Country) => {
      this.selectedCountry = selectedCountry;
    });

    this.filter.page = this.page;
    this.filter.pageSize = this.pageSize;
    this.personsResponse = await this.adminServiceService.getAdmins(this.filter);
  }

  async submitAdminForm() {
    console.log(this.adminForm.value);
    if (this.adminForm.valid) {
      this.adminServiceService.createAdmin(this.adminForm.value);
    } else {
      console.log('Form is not valid');
    }
  }

  async submitFilterForm() {
    if (this.filterForm.valid) {
      this.filter = this.filterForm.value;
      this.personsResponse = await this.adminServiceService.getAdmins(this.filter);
    } else {
      console.log('Form is not valid');
    }
  }

  handleFileSelected(fileBase64 : string) {
    this.adminForm.patchValue({
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
    if (action === 'next' && ! this.personsResponse.last) {
      this.filter.page = this.personsResponse.number+1;
    } else if (action === 'prev' && ! this.personsResponse.first) {
      this.filter.page = this.personsResponse.number-1;
    }
    if ((action === 'next' && ! this.personsResponse.last) 
      || (action === 'prev' && ! this.personsResponse.first)) {
        this.filter.pageSize = this.pageSize;
        this.personsResponse = await this.adminServiceService.getAdmins(this.filter);
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
    this.personsResponse = await this.adminServiceService.getAdmins(this.filterForm.value);
  }

  async refresh() {
    this.personsResponse = await this.adminServiceService.getAdmins(this.filter);
  }

  async clearForm() {
    this.adminForm = this.formBuilder.group({
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

    this.defaultProfile = this.profiles.find(profile => profile.code === 'admin');
    this.adminForm.patchValue({
      profile: this.defaultProfile
    });
  }
}
