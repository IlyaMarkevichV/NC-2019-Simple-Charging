import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { StartPageComponent } from './start-page/start-page.component';
import { RouterModule, Routes } from "@angular/router";
import { NavbarComponent } from './navbar/navbar.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { SignInComponent } from './sign-in/sign-in.component';

const appRoutes: Routes = [
  {path: '', component:StartPageComponent},
  {path: 'login', component:LoginPageComponent},
  {path: 'signin', component:LoginPageComponent},
]

@NgModule({
  declarations: [
    AppComponent,
    StartPageComponent,
    NavbarComponent,
    LoginPageComponent,
    SignInComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }


