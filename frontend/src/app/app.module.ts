import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {RouterModule, Routes} from "@angular/router";
import {NavbarComponent} from './modules/navbar/navbar.component';
import {SignInComponent} from './modules/sign-in/sign-in.component';
import {ModalComponent} from './modules/log-in/modal.component';
import {MainpageComponent} from './modules/mainpage/mainpage.component';
import {CardComponent} from './modules/product/card.component';
import {FooterComponent} from './modules/footer/footer.component';
import {WalletsPageComponent} from './modules/wallets-page/wallets-page.component';
import {SubscriptionsPageComponent} from './modules/subscriptions-page/subscriptions-page.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RegistrService} from "./service/registr/registr.service";
import {Interceptor} from "./app.interceptor";
import {MainService} from "./service/main/main.service";
import {TokenStorage} from "./storage/tokenStorage";
import {LogUserService} from "./service/login/log.service";
import {Guard} from "./guard";
import {MainPageService} from "./service/mainPage/mainPage.service";
import {WalletPageService} from "./service/walletPage/walletPage.service";
import {SubPageService} from "./service/subPage/subPage.service";
import {UserStorage} from "./storage/userStorage";
import {UsersPageComponent } from './modules/users-page/users-page.component';
import {UsersPageService} from "./service/usersPage/usersPage.service";
import { FieldErrorDisplayComponent } from './modules/field-error-display/field-error-display.component';

const appRoutes: Routes = [
  {path: 'home', component:MainpageComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'signin', component:SignInComponent},
  {path: 'subscriptions', component:SubscriptionsPageComponent},
  {path: 'wallets', component:WalletsPageComponent},
  {path: 'product/:id', component:CardComponent},
  {path: 'users', component:UsersPageComponent},
]

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SignInComponent,
    ModalComponent,
    MainpageComponent,
    CardComponent,
    FooterComponent,
    WalletsPageComponent,
    SubscriptionsPageComponent,
    UsersPageComponent,
    FieldErrorDisplayComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    ReactiveFormsModule
  ],
  providers: [RegistrService, MainService, TokenStorage, LogUserService, Guard, MainPageService,
    WalletPageService, SubPageService, UserStorage, UsersPageService,
    {
      provide: HTTP_INTERCEPTORS,
      // Этим interceptor`ом будем добавлять auth header
      useClass: Interceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }


