import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthComponent } from './auth/auth.component';
import { BookmarkComponent } from './bookmark/bookmark.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { CardComponent } from './card/card.component';
import { OrderComponent } from './order/order.component';

const routes: Routes = [

  {
    path: "auth/login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "auth",
    component: AuthComponent
  },
  {
    path: "order",
    component: OrderComponent
  },
  {
    path: "admin-profile",
    component: AdminDashboardComponent
  },
  {
    path: "card",
    component: CardComponent
  },   
  {
    path: "bookmark",
    component: BookmarkComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
