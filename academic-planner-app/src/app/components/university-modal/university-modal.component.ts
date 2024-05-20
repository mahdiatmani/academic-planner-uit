import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IonModal, ModalController } from '@ionic/angular';
import { City } from 'src/app/models/City';
import { University } from 'src/app/models/University';
import { KernelServiceService } from '../../services/kernel-service.service';
import { Country } from 'src/app/models/Country';
import { AdminServiceService } from 'src/app/services/admin-service.service';

@Component({
  selector: 'app-university-modal',
  templateUrl: './university-modal.component.html',
  styleUrls: ['./university-modal.component.scss'],
})
export class UniversityModalComponent implements OnInit {

  @Input() university: University;
  universityForm: FormGroup;
  countries: Country[];
  selectedCountry: Country;
  selectedFile: File;

  constructor(
    private modalCtrl: ModalController,
    private formBuilder: FormBuilder,
    private kernelServiceService: KernelServiceService,
    private adminServiceService: AdminServiceService,
  ) { }

  async ngOnInit() {
    this.universityForm = this.formBuilder.group({
      id: [this.university.id, [Validators.required]],
      code: [this.university.code, [Validators.required]],
      name: [this.university.name, [Validators.required]],
      description: [this.university.description, Validators.required],
      thumbnail: [this.university.thumbnail, Validators.required],
      citizenship: ['', Validators.required],
      city: [this.university.city, Validators.required],
    });

    this.countries = await this.kernelServiceService.countriesGet();

    this.universityForm.get('citizenship')?.valueChanges.subscribe((selectedCountry: Country) => {
      this.selectedCountry = selectedCountry;
    });

  }
  async submitForm() {
    if (this.universityForm.valid) {
      const university = await this.adminServiceService.saveUniversity(this.universityForm.value);
      this.universityForm.reset();
      this.modalCtrl.dismiss({ university : university });
    } else {
      console.log('Form is not valid');
    }
  }

  closeModal() {
    this.modalCtrl.dismiss();
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    this.selectedFile = file;
    this.previewImage(file);
  }

  previewImage(file: File) {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.universityForm.patchValue({
        thumbnail: reader.result as string
      });
    };
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
