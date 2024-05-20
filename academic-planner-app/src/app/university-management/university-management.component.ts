import { Component, OnInit } from '@angular/core';
import { KernelServiceService } from '../services/kernel-service.service';
import { University } from '../models/University';
import { UniversityModalComponent } from '../components/university-modal/university-modal.component';
import { ModalController } from '@ionic/angular';
import { EstablishmentModalComponent } from '../components/establishment-modal/establishment-modal.component';
import { Establishment } from '../models/Establishment';
import { Router } from '@angular/router';

@Component({
  selector: 'app-university-management',
  templateUrl: './university-management.component.html',
  styleUrls: ['./university-management.component.scss'],
})
export class UniversityManagementComponent  implements OnInit {

  university        : University;
  establishments    : Establishment[];

  constructor(
    private kernelService : KernelServiceService,
    private modalCtrl: ModalController,
    private router: Router
  ) { }

  async ngOnInit() {   
    this.university = await this.kernelService.universityGet();
    this.establishments = await this.kernelService.establishmentsGet();
  }

  async openUniversityModal() {
    const modal = await this.modalCtrl.create({
      component: UniversityModalComponent,
      cssClass: 'card-modal',
      componentProps: {
        university: this.university
      }
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
    if(data) this.university = data.university;
  }

  async openAddEstablishmentModal() {
    const modal = await this.modalCtrl.create({
      component: EstablishmentModalComponent,
      cssClass: 'card-modal',
      componentProps: {
        university: this.university
      }
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
    if(data) this.establishments.push(data.establishment);
  }

  async openEstablishmentModal(establishment : Establishment) {
    const modal = await this.modalCtrl.create({
      component: EstablishmentModalComponent,
      cssClass: 'card-modal',
      componentProps: {
        establishment: establishment,
        university: this.university
      }
    });
    await modal.present();
    const { data } = await modal.onWillDismiss();
    if(data) {
      const index = this.establishments.findIndex(e => e.id === data.establishment.id);
      if (index !== -1) {
        this.establishments[index] = data.establishment;
      }
    }
  }

  openEstablishmentPage(code: string) {
    this.router.navigate([`/establishment/${code}`]);
  }

}
