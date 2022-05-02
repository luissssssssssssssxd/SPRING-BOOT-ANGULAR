import { Area } from "../areas/area";
import { Costo } from "../centrocosto/costo";
import { Estado } from "../estado/estado";
import { Marca } from "../marcas/marca";
import { Modelo } from "../modelo/modelo";
import { Piso } from "../piso/piso";
import { Sociedad } from "../sociedad/sociedad";
import { Ubicacion } from "../ubicacion/ubicacion";

export class Impresora{
    id:any;
    numeroserie: string;
    fecha_mov: Date;
    observacion: string;
    ip:any;
    area: Area[];
    estado: Estado[];
    marca: Marca[];
    modelo: Modelo[];
    costo:Costo[];
    piso:Piso[];
    ubicacion:Ubicacion[];
    sociedad:Sociedad[];
}
