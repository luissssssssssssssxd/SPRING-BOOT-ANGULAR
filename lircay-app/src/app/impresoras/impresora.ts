import { Area } from "../areas/area";
import { Estado } from "../estado/estado";
import { Marca } from "../marcas/marca";
import { Modelo } from "../modelo/modelo";

export class Impresora{
    id:any;
    numeroserie: string;
    fecha_mov: Date;
    observacion: string;
    area: Area[];
    estado: Estado[];
    marca: Marca[];
    modelo: Modelo[];
}
