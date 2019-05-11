import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {RouterModule, Routes} from "@angular/router";
import {NavbarComponent} from './modules/navbar/navbar.component';
import {SignInComponent} from './modules/sign-in/sign-in.component';
import {ModalComponent} from './modules/log-in/modal.component';
import {MainpageComponent} from './modules/mainpage/mainpage.component';
import {CardComponent} from './modules/product/card.component';
import {FooterComponent} from './modules/footer/footer.component';
import {WalletsPageComponent} from './modules/wallets-page/wallets-page.component';
import {WalletComponent} from './modules/wallet/wallet.component';
import {SubscriptionsPageComponent} from './modules/subscriptions-page/subscriptions-page.component';
import {SubCardComponent} from './modules/subscription/sub-card.component';
import {FormsModule} from "@angular/forms";
import {RegistrService} from "./service/registr/registr.service";
import {Interceptor} from "./app.interceptor";
import {MainService} from "./service/main/main.service";
import {TokenStorage} from "./token/tokenStorage";
import {LogUserService} from "./service/login/log.service";
import {Guard} from "./guard";
import {MainPageService} from "./service/mainPage/mainPage.service";

const appRoutes: Routes = [
  {path: '', component:MainpageComponent},
  {path: 'signin', component:SignInComponent},
  {path: 'subscriptions', component:SubscriptionsPageComponent},
  {path: 'wallets', component:WalletsPageComponent},
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
    WalletComponent,
    SubscriptionsPageComponent,
    SubCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [RegistrService, MainService, TokenStorage, LogUserService, Guard, MainPageService,
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


