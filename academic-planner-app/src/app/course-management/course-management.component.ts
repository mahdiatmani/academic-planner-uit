import { Component, OnInit } from '@angular/core';
import { Course } from '../models/Course';
import { KernelServiceService } from '../services/kernel-service.service';
import { UtilsService } from '../services/utils.service';
import { ModalController } from '@ionic/angular';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseModalComponent } from '../components/course-modal/course-modal.component';

@Component({
  selector: 'app-course-management',
  templateUrl: './course-management.component.html',
  styleUrls: ['./course-management.component.scss'],
})
export class CourseManagementComponent  implements OnInit {

  code            : string | null;
  course          : Course;

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
      this.course =  await this.kernelService.courseByCodeGet(this.code);
    }
  }

  async openCourseModal() {
    const modal = await this.modalCtrl.create({
      component: CourseModalComponent,
      cssClass: 'card-modal',
      componentProps: {
        course: this.course,
        academicProgram: this.course.academicProgram
      }
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
    if(data) this.course = data.course;
  }

}
