import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductosComponent } from './productos/productos.component';
import { MenuComponent } from './menu/menu.component';
import { VentaProdcutoTemporalComponent } from './venta-prodcuto-temporal/venta-prodcuto-temporal.component';
import { HistorialComponent } from './historial/historial.component';


const routes: Routes = [
  { path: '', redirectTo: '/ventas', pathMatch: 'full' },

  {
    path: '',
    component: MenuComponent,/* 
    canActivate: [AuthService], */
    children: [
      {path: 'productos', component: ProductosComponent},
      {path: 'ventas', component: VentaProdcutoTemporalComponent},
      {path: 'historial', component: HistorialComponent}
    ]}
    
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
