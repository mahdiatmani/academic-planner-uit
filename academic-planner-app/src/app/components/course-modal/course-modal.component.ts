import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalController } from '@ionic/angular';
import { AcademicProgram } from 'src/app/models/AcademicProgram';
import { Course } from 'src/app/models/Course';
import { Department } from 'src/app/models/Department';
import { Establishment } from 'src/app/models/Establishment';
import { Semester } from 'src/app/models/Semester';
import { Teacher } from 'src/app/models/Teacher';
import { AdminServiceService } from 'src/app/services/admin-service.service';
import { KernelServiceService } from 'src/app/services/kernel-service.service';
import { SpinnerService } from 'src/app/services/spinner.service';



@Component({
  selector: 'app-course-modal',
  templateUrl: './course-modal.component.html',
  styleUrls: ['./course-modal.component.scss'],
})
export class CourseModalComponent  implements OnInit {

  @Input() course           : Course;
  @Input() academicProgram  : AcademicProgram;
  courseForm                : FormGroup;
  semesters                 : Semester[];
  establishments            : Establishment[];
  departments               : Department[];
  teachers                  : Teacher[];
  selectedEstablishment     : Establishment;
  selectedDepartment        : Department;


  constructor(
    private modalCtrl: ModalController,
    private formBuilder: FormBuilder,
    private kernelServiceService: KernelServiceService,
    private spinnerService: SpinnerService,
    private adminServiceService: AdminServiceService
  ) { }
  
  async ngOnInit() {
    this.courseForm = this.formBuilder.group({
      id: [this.course ? this.course.id : '' ],
      code: [this.course ? this.course.code : '' , [Validators.required]],
      title: [this.course ? this.course.title : '' , [Validators.required]],
      description: [this.course ? this.course.description : '' , [Validators.required]],
      creditHours: [this.course ? this.course.creditHours : '' , [Validators.required]],
      coefficient: [this.course ? this.course.coefficient : ''],
      academicProgram: [this.course ? this.course.academicProgram : '' , [Validators.required]],
      semester: [this.course ? this.course.semester : '' , [Validators.required]],
      teacher: [this.course ? this.course.teacher : '' , [Validators.required]],
      establishment: [''],
      department: [''],
    });

    if(this.academicProgram) this.courseForm.patchValue({ academicProgram: this.academicProgram });

    this.semesters = await this.kernelServiceService.semestersGet();
    this.establishments = await this.kernelServiceService.establishmentsGet();

    this.courseForm.get('establishment')?.valueChanges.subscribe(async (selectedEstablishment: Establishment) => {
      this.selectedEstablishment = selectedEstablishment;
      this.departments = await this.kernelServiceService.departmentByEstablishmentCodeGet(selectedEstablishment.code);
    });

    this.courseForm.get('department')?.valueChanges.subscribe(async (selectedDepartment: Department) => {
      this.selectedDepartment = selectedDepartment;
      this.teachers = await this.adminServiceService.getTeachersDepartment(selectedDepartment.code);
    });
  }

  async submitForm() {
    console.log(this.courseForm)
    if (this.courseForm.valid) {
      const course = await this.adminServiceService.saveCourse(this.courseForm.value);
      this.courseForm.reset();
      this.modalCtrl.dismiss({ course : course});
    } else {
      this.spinnerService.presentAlert('error','Form is not valid')
    }
  }

  closeModal() {
    this.modalCtrl.dismiss();
  }
}
