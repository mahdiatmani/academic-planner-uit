import { Component, OnInit } from '@angular/core';
import { KernelServiceService } from '../services/kernel-service.service';
import { ModalController } from '@ionic/angular';
import { EstablishmentModalComponent } from '../components/establishment-modal/establishment-modal.component';
import { CourseModalComponent } from '../components/course-modal/course-modal.component';
import { University } from '../models/University';
import { Establishment } from '../models/Establishment';
import { ActivatedRoute, Router } from '@angular/router';
import { Department } from '../models/Department';
import { DepartmentModalComponent } from '../components/department-modal/department-modal.component';
import { UtilsService } from '../services/utils.service';
import { ClassRoom } from '../models/ClassRoom';
import { ClassRoomModalComponent } from '../components/class-room-modal/class-room-modal.component';
import { AdminServiceService } from '../services/admin-service.service';

@Component({
  selector: 'app-establishment-management',
  templateUrl: './establishment-management.component.html',
  styleUrls: ['./establishment-management.component.scss'],
})
export class EstablishmentManagementComponent  implements OnInit {

  code: string | null;
  establishment: Establishment;
  departments: Department[];
  classRooms: ClassRoom[] = [];

  constructor(
    private adminService: AdminServiceService,
    private kernelService: KernelServiceService,
    private utilsService: UtilsService,
    private modalCtrl: ModalController,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  async ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.code = params.get('code');
    });

    if (this.code) {
      this.establishment = await this.kernelService.establishmentByCodeGet(this.code);
      this.departments = await this.kernelService.departmentByEstablishmentCodeGet(this.establishment.code);
      this.classRooms = await this.kernelService.classRoomsByEstablishmentCodeGet(this.establishment.code);
    }
  }

  async openEstablishmentModal() {
    const modal = await this.modalCtrl.create({
      component: EstablishmentModalComponent,
      cssClass: 'card-modal',
      componentProps: {
        establishment: this.establishment
      }
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
    if (data) this.establishment = data.establishment;
  }

  async openCourseModal() {
    const modal = await this.modalCtrl.create({
      component: CourseModalComponent,
      cssClass: 'card-modal',
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
  }

  async openAddDepartmentModal() {
    const modal = await this.modalCtrl.create({
      component: DepartmentModalComponent,
      cssClass: 'card-modal',
      componentProps: {
        establishment: this.establishment
      }
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
    if (data) this.departments.push(data.department);
  }

  async openDepartmentModal(department : Department) {
    const modal = await this.modalCtrl.create({
      component: DepartmentModalComponent,
      cssClass: 'card-modal',
      componentProps: {
        department: department,
        establishment: this.establishment
      }
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
    if (data) {
      const index = this.departments.findIndex(e => e.id === data.department.id);
      if (index !== -1) {
        this.departments[index] = data.department;
      }
    }
  }

  async openAddClassRoomModal() {
    const modal = await this.modalCtrl.create({
      component: ClassRoomModalComponent,
      cssClass: 'card-modal',
      componentProps: {
        establishment: this.establishment
      }
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
    console.log(data)
    if (data) this.classRooms.push(data.classRoom);
  }

  async openClassRoomModal(classRoom: ClassRoom) {
    const modal = await this.modalCtrl.create({
      component: ClassRoomModalComponent,
      cssClass: 'card-modal',
      componentProps: {
        classRoom: classRoom,
        establishment: this.establishment
      }
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
    if (data) {
      const index = this.classRooms.findIndex(e => e.id === data.classRoom.id);
      if (index !== -1) {
        this.classRooms[index] = data.classRoom;
      }
    }
  }

  async deleteClassRoom(classRoom: ClassRoom) {
    await this.adminService.deleteClassRoom(classRoom);
    const index = this.classRooms.findIndex(e => e.id === classRoom.id);
    if (index !== -1) {
      this.classRooms.splice(index, 1);
    }
  }

  openDepartmentPage(code: string) {
    this.router.navigate([`/department/${code}`]);
  }

  getBackground() {
    return this.utilsService.generateRandomSvgBackground();
  }
}
