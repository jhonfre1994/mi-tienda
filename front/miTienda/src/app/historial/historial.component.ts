import { Component, OnInit } from '@angular/core';
import { HistorialService } from './historial.service';
import { HistorialDTO } from '../model/HistorialDTO';
import { ReporteMesDTO } from '../model/ReporteMesDTO';
import { ConfirmationService, Message } from 'primeng/api';

@Component({
  selector: 'app-historial',
  templateUrl: './historial.component.html',
  styleUrls: ['./historial.component.css'],
  providers: [HistorialService, ConfirmationService]
})
export class HistorialComponent implements OnInit {
  msgs: Message[] = [];
  public fecha: Date;
  public listaHistorial: Array<HistorialDTO> = [];
  public reporte: ReporteMesDTO;

  constructor(private historialService: HistorialService,
    private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
  }

  listarTodos() {
    this.reporte = new ReporteMesDTO();
    this.listaHistorial = [];
    this.historialService.listarHistoriales(this.fecha).subscribe(res => {
      if (res != null) {
        console.log(res)
        this.reporte = res
        this.listaHistorial = res.historial;
      }
    })
  }

  eliminarDato(id: number) {
    this.historialService.eliminar(id).subscribe(res => {
      if (res == true) {
        this.listarTodos();
      }
    })
  }


  confirmar(item: HistorialDTO) {
    console.log(item)
    this.confirmationService.confirm({
      message: 'Esta seguro que desea eliminar este elemento?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.eliminarDato(item.idHistorial)
      },
      reject: () => {

      }
    });
  }

}
