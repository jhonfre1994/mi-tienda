import { Component, OnInit } from '@angular/core';
import { SelectItem, MenuItem } from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  items: MenuItem[];
  mostrarmenu: boolean = false;
  nombeUsuario: string;
  bienvenido : string;

  constructor(public router: Router, private _route: ActivatedRoute) { }

  ngOnInit(): void {
    this.items = [
      {
        label: 'Opciones',
        icon: 'pi pi-fw pi-pencil',
        items: [
          {
            label: 'Registrar notas', icon: 'pi pi-fw pi-chart-line',
            command: (event) => {
              this.enviarProductos();
            }
          },
        ]
      },
    ];
  }

  enviarProductos() {
    this.router.navigate(['productos'])
    this.mostrarmenu = false;

  }

  mostrarmenus(): boolean {
    return this.mostrarmenu = true;
  }
}
