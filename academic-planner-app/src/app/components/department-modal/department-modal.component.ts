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

@Component({
  selector: 'app-department-modal',
  templateUrl: './department-modal.component.html',
  styleUrls: ['./department-modal.component.scss'],
})
export class DepartmentModalComponent  implements OnInit {

  @Input() department   : Department;
  @Input() establishment: Establishment;
  departmentForm        : FormGroup;
  

  constructor(
    private modalCtrl: ModalController,
    private formBuilder: FormBuilder,
    private kernelServiceService: KernelServiceService,
    private spinnerService: SpinnerService,
    private adminServiceService: AdminServiceService
  ) { }
  
  async ngOnInit() {
    this.departmentForm = this.formBuilder.group({
      id: [this.department ? this.department.id : '' ],
      code: [this.department ? this.department.code : '' , [Validators.required]],
      name: [this.department ? this.department.name : '' , [Validators.required]],
      description: [this.department ? this.department.description : '' , [Validators.required]],
      establishment: [this.department ? this.department.establishment : '' , [Validators.required]],
    });

    if (! this.department) this.departmentForm.patchValue({ establishment: this.establishment });
  }

  async submitForm() {
    if (this.departmentForm.valid) {
      const department = await this.adminServiceService.saveDepartment(this.departmentForm.value);
      this.departmentForm.reset();
      this.modalCtrl.dismiss({ department : department});
    } else {
      this.spinnerService.presentAlert('error','Form is not valid')
    }
  }

  closeModal() {
    this.modalCtrl.dismiss();
  }

}
