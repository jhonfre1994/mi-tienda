import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductosComponent } from './productos/productos.component';
import { MenuComponent } from './menu/menu.component';


const routes: Routes = [
  { path: '', redirectTo: '/productos', pathMatch: 'full' },

  {
    path: '',
    component: MenuComponent,/* 
    canActivate: [AuthService], */
    children: [
      {path: 'productos', component: ProductosComponent}
    ]}
    
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
