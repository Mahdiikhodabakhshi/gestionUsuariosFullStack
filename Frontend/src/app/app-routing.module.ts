import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from './componets/inicio/inicio.component';
import { UsersComponent } from './componets/users/users.component';
import {NotFoundedComponent} from "./componets/not-founded/not-founded.component";


const routes: Routes = [
  {
    path:'',redirectTo:'/inicio' , pathMatch:'full'
  },
  {
    path:'inicio' , component:InicioComponent
  },
  {
    path:'users' , component:UsersComponent
  },
  {
    path:'**',component:NotFoundedComponent , pathMatch:'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
