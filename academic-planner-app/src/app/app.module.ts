import { APP_INITIALIZER, CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouteReuseStrategy } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
import { HttpClient, HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './home/home.component';
import { BaseComponent } from './base/base.component';
import { CommonModule } from '@angular/common';
import { ConfigurationService } from './services/configuration.service';
import { environment } from 'src/environments/environment';
import { SpinnerComponent } from './components/spinner/spinner.component';
import { PortalModule } from '@angular/cdk/portal';
import { SkeletonItemComponent } from './components/skeleton-item/skeleton-item.component';
import { SkeletonImageComponent } from './components/skeleton-image/skeleton-image.component';
import { ClipboardDirective } from './directives/clipboard.directive';
import { UsersManagementComponent } from './users-management/users-management.component';
import { UniversityManagementComponent } from './university-management/university-management.component';
import { SchedulesManagementComponent } from './schedules-management/schedules-management.component';
import { AbsencesManagementComponent } from './absences-management/absences-management.component';
import { EstablishmentModalComponent } from './components/establishment-modal/establishment-modal.component';
import { UniversityModalComponent } from './components/university-modal/university-modal.component';
import { DepartmentModalComponent } from './components/department-modal/department-modal.component';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { ProfilePictureComponent } from './components/profile-picture/profile-picture.component';
import { StudentsManagementComponent } from './components/students-management/students-management.component';
import { TeachersManagementComponent } from './components/teachers-management/teachers-management.component';
import { AdminsManagementComponent } from './components/admins-management/admins-management.component';
import { UserPopoverComponent } from './base/user-popover/user-popover.component';
import { LoginComponent } from './login/login.component';
import { CustomDatePipe } from './pipes/custom-date-pipe.pipe';
import { CourseModalComponent } from './components/course-modal/course-modal.component';
import { EstablishmentManagementComponent } from './establishment-management/establishment-management.component';
import { DepartmentManagementComponent } from './department-management/department-management.component';
import { AcademicProgramModalComponent } from './components/academic-program-modal/academic-program-modal.component';
import { AcademicProgramManagementComponent } from './academic-program-management/academic-program-management.component';
import { CourseManagementComponent } from './course-management/course-management.component';
import { ClassRoomModalComponent } from './components/class-room-modal/class-room-modal.component';

export function ConfigLoader(configurationService: ConfigurationService) {
  return () => configurationService.load(environment.configFile);
}

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}


@NgModule({
  declarations: [
    AppComponent,
    BaseComponent,
    SpinnerComponent,
    SkeletonItemComponent,
    SkeletonImageComponent,
    ClipboardDirective,
    HomeComponent,
    UsersManagementComponent,
    UniversityManagementComponent,
    SchedulesManagementComponent,
    AbsencesManagementComponent,
    UniversityModalComponent,
    EstablishmentModalComponent,
    DepartmentModalComponent,
    ProfilePictureComponent,
    StudentsManagementComponent,
    TeachersManagementComponent,
    AdminsManagementComponent,
    UserPopoverComponent,
    LoginComponent,
    CustomDatePipe,
    CourseModalComponent,
    EstablishmentManagementComponent,
    DepartmentManagementComponent,
    AcademicProgramModalComponent,
    AcademicProgramManagementComponent,
    CourseManagementComponent,
    ClassRoomModalComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    IonicModule.forRoot({
      mode: 'md',
    }),
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
    HttpClientModule,
    PortalModule,
    HttpClientModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: HttpLoaderFactory,
                deps: [HttpClient]
            }
        }),
  ],
  providers: [
    ClipboardDirective,
    ConfigurationService,
    {
    provide: APP_INITIALIZER,
    useFactory: ConfigLoader,
    deps: [ConfigurationService],
    multi: true
    },
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy }],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],

})
export class AppModule {}
