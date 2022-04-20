import { Area } from "../areas/area";
import { Estado } from "../estado/estado";
import { Marca } from "../marcas/marca";
import { Modelos } from "../marcas/modelos";

export class Impresora{
    id:any;
    numeroserie: string;
    fecha_mov: Date;
    obs: string;
    area: Area[];
    estado: Estado['estadoimpresora'];
    marca: Marca[];
    modelo: Modelos[];
}