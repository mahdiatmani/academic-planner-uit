import { Component, OnInit } from '@angular/core';
import { Course } from '../models/Course';
import { AcademicProgram } from '../models/AcademicProgram';
import { KernelServiceService } from '../services/kernel-service.service';
import { UtilsService } from '../services/utils.service';
import { ModalController } from '@ionic/angular';
import { ActivatedRoute, Router } from '@angular/router';
import { DepartmentModalComponent } from '../components/department-modal/department-modal.component';
import { CourseModalComponent } from '../components/course-modal/course-modal.component';
import { AcademicProgramModalComponent } from '../components/academic-program-modal/academic-program-modal.component';

@Component({
  selector: 'app-academic-program-management',
  templateUrl: './academic-program-management.component.html',
  styleUrls: ['./academic-program-management.component.scss'],
})
export class AcademicProgramManagementComponent  implements OnInit {

  code                : string | null;
  academicProgram     : AcademicProgram;
  courses             : Course[];

  constructor(
    private kernelService : KernelServiceService,
    private utilsService: UtilsService,
    private modalCtrl: ModalController,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  async ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.code = params.get('code');
    });  
    
    if(this.code) {
      this.academicProgram =  await this.kernelService.academicProgramByCodeGet(this.code);
      this.courses  = await this.kernelService.coursesByAcademicProgramCodeGet(this.academicProgram.code);
    }
  }

  async openAcademicProgramModal() {
    const modal = await this.modalCtrl.create({
      component: AcademicProgramModalComponent,
      cssClass: 'card-modal',
      componentProps: {
        academicProgram: this.academicProgram,
        department: this.academicProgram.department
      }
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
    if(data) this.academicProgram = data.academicProgram;
  }

  async openAddCourseModal() {
    const modal = await this.modalCtrl.create({
      component: CourseModalComponent,
      cssClass: 'card-modal',
      componentProps: {
        academicProgram: this.academicProgram
      }
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
    if(data) this.courses.push(data.course);
  }

  async openCourseModal(course : Course) {
    const modal = await this.modalCtrl.create({
      component: CourseModalComponent,
      cssClass: 'card-modal',
      componentProps: {
        course: course,
        academicProgram: this.academicProgram
      }
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
    if(data) {
      const index = this.courses.findIndex(e => e.id === data.course.id);
      if (index !== -1) {
        this.courses[index] = data.course;
      }
    }
  }

  openCoursePage(code: string) {
    this.router.navigate([`/course/${code}`]);
  }

  getBackground() {
    return this.utilsService.generateRandomSvgBackground();
  }

}
