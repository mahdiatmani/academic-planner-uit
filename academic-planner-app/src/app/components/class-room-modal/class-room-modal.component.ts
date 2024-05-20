import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalController } from '@ionic/angular';
import { ClassRoom } from 'src/app/models/ClassRoom';
import { Establishment } from 'src/app/models/Establishment';
import { AdminServiceService } from 'src/app/services/admin-service.service';
import { KernelServiceService } from 'src/app/services/kernel-service.service';
import { SpinnerService } from 'src/app/services/spinner.service';

@Component({
  selector: 'app-class-room-modal',
  templateUrl: './class-room-modal.component.html',
  styleUrls: ['./class-room-modal.component.scss'],
})
export class ClassRoomModalComponent  implements OnInit {

  @Input() classRoom   : ClassRoom;
  @Input() establishment: Establishment;
  classRoomForm        : FormGroup;

  constructor(
    private modalCtrl: ModalController,
    private formBuilder: FormBuilder,
    private kernelServiceService: KernelServiceService,
    private spinnerService: SpinnerService,
    private adminServiceService: AdminServiceService
  ) {
   }

  ngOnInit() {
    this.classRoomForm = this.formBuilder.group({
      id: [this.classRoom ? this.classRoom.id : '' ],
      code: [this.classRoom ? this.classRoom.code : '' , [Validators.required]],
      name: [this.classRoom ? this.classRoom.name : '' , [Validators.required]],
      establishment: [this.classRoom ? this.classRoom.establishment : '' , [Validators.required]],
    });

    if (! this.classRoom) this.classRoomForm.patchValue({ establishment: this.establishment });
  }

async submitForm() {
    if (this.classRoomForm.valid) {
      const classRoom = await this.adminServiceService.saveClassRoom(this.classRoomForm.value);
      this.classRoomForm.reset();
      this.modalCtrl.dismiss({ classRoom : classRoom});
    } else {
      this.spinnerService.presentAlert('error','Form is not valid')
    }
  }
  closeModal() {
    this.modalCtrl.dismiss();
  }

}
