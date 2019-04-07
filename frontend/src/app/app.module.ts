import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { RouterModule, Routes } from "@angular/router";
import { NavbarComponent } from './navbar/navbar.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { ModalComponent } from './modal/modal.component';
import { MainpageComponent } from './mainpage/mainpage.component';
import { CardComponent } from './card/card.component';
import { FooterComponent } from './footer/footer.component';
import { WalletsPageComponent } from './wallets-page/wallets-page.component';
import { WalletComponent } from './wallet/wallet.component';
import { SubscriptionsPageComponent } from './subscriptions-page/subscriptions-page.component';
import { SubCardComponent } from './sub-card/sub-card.component';

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
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }


