import { ChangeDetectorRef, Component, ElementRef, HostListener, OnInit, ViewChild } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { UserServiceService } from '../services/user-service.service';
import Swiper from 'swiper';
import { SwiperOptions } from 'swiper/types';
import { UtilsService } from '../services/utils.service';
import { GlobalConfig } from '../models/GlobalConfig';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { City } from '../models/City';
import { Profile } from '../models/Profile';
import { LegalIdType } from '../models/LegalIdType';
import { Country } from '../models/Country';
import { KernelServiceService } from '../services/kernel-service.service';
import { University } from '../models/University';
import { AdminServiceService } from '../services/admin-service.service';
import { SpinnerService } from '../services/spinner.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent  implements OnInit {

  studentForm : FormGroup;
  teacherForm : FormGroup;
  adminForm   : FormGroup;
  baseSize    : number = 4;
  global      : GlobalConfig;
  countries   : Country[];
  profiles    : Profile[];
  legalIdTypes : LegalIdType[];
  university   : University;
  selectedStudentCountry: Country;
  selectedTeacherCountry: Country;
  selectedAdminCountry: Country;

  constructor(
    private utilsService            : UtilsService,
    private modalCtrl               : ModalController,
    private userServiceService      : UserServiceService,
    private cdr                     : ChangeDetectorRef,
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
      birthDate: ['', Validators.required],
      legalIdNumber: ['', Validators.required],
      legalIdType: ['', Validators.required],
      citizenship: ['', Validators.required],
      profile: ['', Validators.required],
      city: ['', Validators.required],
      apogeeCode: ['', Validators.required],
      studentNationalCode: ['', Validators.required]
    });

    this.teacherForm = this.formBuilder.group({
      gender: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      birthDate: ['', Validators.required],
      legalIdNumber: ['', Validators.required],
      legalIdType: ['', Validators.required],
      citizenship: ['', Validators.required],
      profile: ['', Validators.required],
      city: ['', Validators.required],
    });

    this.adminForm = this.formBuilder.group({
      gender: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      birthDate: ['', Validators.required],
      legalIdNumber: ['', Validators.required],
      legalIdType: ['', Validators.required],
      citizenship: ['', Validators.required],
      profile: ['', Validators.required],
      city: ['', Validators.required],
    });

    this.countries = await this.kernelServiceService.countriesGet();
    this.legalIdTypes = await this.kernelServiceService.legalIdTypesGet();
    this.profiles = await this.kernelServiceService.profilesGet();
    this.university = await this.kernelServiceService.universityGet();

    this.studentForm.get('citizenship')?.valueChanges.subscribe((selectedCountry: Country) => {
      this.selectedStudentCountry = selectedCountry;
    });

    this.teacherForm.get('citizenship')?.valueChanges.subscribe((selectedCountry: Country) => {
      this.selectedTeacherCountry = selectedCountry;
    });

    this.teacherForm.get('citizenship')?.valueChanges.subscribe((selectedCountry: Country) => {
      this.selectedAdminCountry = selectedCountry;
    });
    
  }

  async submitStudentForm() {
    if (this.studentForm.valid) {
      this.adminServiceService.createStudent(this.studentForm.value);
    } else {
      console.log('Form is not valid');
    }
  }

  async submitTeacherForm() {
    if (this.teacherForm.valid) {
      this.adminServiceService.createTeacher(this.teacherForm.value);
    } else {
      console.log('Form is not valid');
    }
  }

  async submitAdminForm() {
    if (this.adminForm.valid) {
      this.adminServiceService.createAdmin(this.adminForm.value);
    } else {
      console.log('Form is not valid');
    }
  }

}
