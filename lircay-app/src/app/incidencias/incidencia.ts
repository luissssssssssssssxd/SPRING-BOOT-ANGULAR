import { Impresora } from "../impresoras/impresora";

export class Incidencia{
    id: any;
    fecha_fin: Date;
    fecha_inicio: Date;
    impresorareemplazo:String;
    impresora: Impresora[];
}
