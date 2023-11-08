export interface Ejercicio {
    id?: number,
      title: string,
      description?: string,
      media?: string,
      unit?: string
}
export interface EjercicioR {
  exerciseId:number,
  quantity?:number,
  repetitions?:number,
  series?:number
}