import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './common/header/header.component';
import { FooterComponent } from './common/footer/footer.component';
import { InicioComponent } from './componets/inicio/inicio.component';
import { UsersComponent } from './componets/users/users.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {HttpClientModule , HTTP_INTERCEPTORS} from "@angular/common/http";
import {HttpRequestInterceptor} from "./config/interceptors/http-request-iterceptor.interceptor";
import {FormsModule , ReactiveFormsModule} from "@angular/forms";
import { NotFoundedComponent } from './componets/not-founded/not-founded.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    InicioComponent,
    UsersComponent,
    NotFoundedComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpRequestInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
