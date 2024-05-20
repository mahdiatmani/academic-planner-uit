import { Component, OnInit } from '@angular/core';
import { KernelServiceService } from '../services/kernel-service.service';
import { ModalController } from '@ionic/angular';
import { CourseModalComponent } from '../components/course-modal/course-modal.component';
import { ActivatedRoute, Router } from '@angular/router';
import { Department } from '../models/Department';
import { DepartmentModalComponent } from '../components/department-modal/department-modal.component';
import { UtilsService } from '../services/utils.service';
import { AcademicProgram } from '../models/AcademicProgram';
import { AcademicProgramModalComponent } from '../components/academic-program-modal/academic-program-modal.component';
import { Establishment } from '../models/Establishment';

@Component({
  selector: 'app-department-management',
  templateUrl: './department-management.component.html',
  styleUrls: ['./department-management.component.scss'],
})
export class DepartmentManagementComponent  implements OnInit {

  code                : string | null;
  department          : Department;
  academicPrograms    : AcademicProgram[];

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
      this.department =  await this.kernelService.departmentByCodeGet(this.code);
      this.academicPrograms  = await this.kernelService.academicProgramsByDepartmentCodeGet(this.department.code);
    }
  }

  async openDepartmentModal() {
    const modal = await this.modalCtrl.create({
      component: DepartmentModalComponent,
      cssClass: 'card-modal',
      componentProps: {
        department: this.department,
        astablishment: this.department.establishment
      }
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
    if(data) this.department = data.department;
  }

  async openCourseModal() {
    const modal = await this.modalCtrl.create({
      component: CourseModalComponent,
      cssClass: 'card-modal',
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
  }

  async openAddAcademicProgramModal() {
    const modal = await this.modalCtrl.create({
      component: AcademicProgramModalComponent,
      cssClass: 'card-modal',
      componentProps: {
        department: this.department
      }
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
    if(data) this.academicPrograms.push(data.academicProgram);
  }

  async openAcademicProgramModal(academicProgram : AcademicProgram) {
    const modal = await this.modalCtrl.create({
      component: AcademicProgramModalComponent,
      cssClass: 'card-modal',
      componentProps: {
        academicProgram: academicProgram,
        department: this.department
      }
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
    if(data) {
      const index = this.academicPrograms.findIndex(e => e.id === data.academicProgram.id);
      if (index !== -1) {
        this.academicPrograms[index] = data.academicProgram;
      }
    }
  }

  openAcademicProgramPage(code: string) {
    this.router.navigate([`/academic-program/${code}`]);
  }

  getBackground() {
    return this.utilsService.generateRandomSvgBackground();
  }
}
