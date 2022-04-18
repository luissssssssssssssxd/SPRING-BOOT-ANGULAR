import { Area } from "../areas/area";
import { Estado } from "../estado/estado";
import { Marca } from "../marcas/marca";
import { Modelos } from "../marcas/modelos";

export class Impresora{
    numeroserie: string;
    fecha_mov: Date;
    obs: string;
    area: Area[];
    estado: Estado[];
    marca: Marca[];
    modelo: Modelos[];
}