import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IonModal, ModalController } from '@ionic/angular';
import { Establishment } from 'src/app/models/Establishment';
import { KernelServiceService } from '../../services/kernel-service.service';
import { Country } from 'src/app/models/Country';
import { City } from 'src/app/models/City';
import { SpinnerService } from 'src/app/services/spinner.service';
import { AdminServiceService } from 'src/app/services/admin-service.service';
import { University } from 'src/app/models/University';
import { Department } from 'src/app/models/Department';
import { AcademicProgram } from 'src/app/models/AcademicProgram';
import { Degree } from 'src/app/models/Degree';

@Component({
  selector: 'app-academic-program-modal',
  templateUrl: './academic-program-modal.component.html',
  styleUrls: ['./academic-program-modal.component.scss'],
})
export class AcademicProgramModalComponent  implements OnInit {
  
  @Input() academicProgram  : AcademicProgram;
  @Input() department       : Department;
  academicProgramForm       : FormGroup;
  degrees                   : Degree[];
  

  constructor(
    private modalCtrl: ModalController,
    private formBuilder: FormBuilder,
    private kernelServiceService: KernelServiceService,
    private spinnerService: SpinnerService,
    private adminServiceService: AdminServiceService
  ) { }
  
  async ngOnInit() {
    this.academicProgramForm = this.formBuilder.group({
      id: [this.academicProgram ? this.academicProgram.id : '' ],
      code: [this.academicProgram ? this.academicProgram.code : '' , [Validators.required]],
      name: [this.academicProgram ? this.academicProgram.name : '' , [Validators.required]],
      description: [this.academicProgram ? this.academicProgram.description : '' , [Validators.required]],
      department: [this.academicProgram ? this.academicProgram.department : '' , [Validators.required]],
      degree: [this.academicProgram ? this.academicProgram.degree : '' , [Validators.required]],
    });

    this.academicProgramForm.patchValue({
      department: this.department
    });

    this.degrees = await this.kernelServiceService.degreesGet();
  }

  async submitForm() {
    if (this.academicProgramForm.valid) {
      const academicProgram = await this.adminServiceService.saveAcademicProgram(this.academicProgramForm.value);
      this.academicProgramForm.reset();
      this.modalCtrl.dismiss({ academicProgram : academicProgram});
    } else {
      this.spinnerService.presentAlert('error','Form is not valid')
    }
  }

  closeModal() {
    this.modalCtrl.dismiss();
  }
}
