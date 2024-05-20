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



@Component({
  selector: 'app-establishment-modal',
  templateUrl: './establishment-modal.component.html',
  styleUrls: ['./establishment-modal.component.scss'],
})
export class EstablishmentModalComponent  implements OnInit {
  
  @Input() establishment: Establishment;
  @Input() university: University;

  establishmentForm: FormGroup;
  countries: Country[];
  selectedCountry: Country;
  selectedFile: File;

  constructor(
    private modalCtrl: ModalController,
    private formBuilder: FormBuilder,
    private kernelServiceService: KernelServiceService,
    private spinnerService: SpinnerService,
    private adminServiceService: AdminServiceService
  ) { }
  


  async ngOnInit() {
    this.establishmentForm = this.formBuilder.group({
      id: [this.establishment ? this.establishment.id : '' ],
      code: [this.establishment ? this.establishment.code : '' , [Validators.required]],
      name: [this.establishment ? this.establishment.name : '' , [Validators.required]],
      description: [this.establishment ? this.establishment.description : '' , Validators.required],
      thumbnail: [this.establishment ? this.establishment.thumbnail : '' , Validators.required],
      citizenship: [''],
      city: [this.establishment ? this.establishment.city : '' , Validators.required],
      university: [this.establishment ? this.establishment.university : '' , Validators.required]

    });

    this.establishmentForm.patchValue({
      university: this.university
    });

    this.countries = await this.kernelServiceService.countriesGet();
    
    this.establishmentForm.get('citizenship')?.valueChanges.subscribe((selectedCountry: Country) => {
      this.selectedCountry = selectedCountry;
    });

  }

  previewImage(file: File) {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.establishmentForm.patchValue({
        thumbnail: reader.result as string
      });
    };
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    this.selectedFile = file;
    this.previewImage(file);
  }

  async submitForm() {
    if (this.establishmentForm.valid) {
      const establishment = await this.adminServiceService.saveEstablishment(this.establishmentForm.value);
      this.establishmentForm.reset();
      this.modalCtrl.dismiss({ establishment : establishment});
    } else {
      this.spinnerService.presentAlert('error','Form is not valid')
    }
  }

  closeModal() {
    this.modalCtrl.dismiss();
  }

  base64ToBlob(base64String: string) {
    const byteString = atob(base64String.split(',')[1]);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const uint8Array = new Uint8Array(arrayBuffer);
    for (let i = 0; i < byteString.length; i++) {
      uint8Array[i] = byteString.charCodeAt(i);
    }
    return new Blob([uint8Array], { type: 'image/jpeg' });
  }

  
}
