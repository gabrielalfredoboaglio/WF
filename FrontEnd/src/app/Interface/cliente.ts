export interface Cliente {   
    id?: number,
    email?: string,
    password?: string,
    name: string,
    profileImg?: string,
    weight: number,
    height: number,
    sex?: string,
    age: number,
    medical_indication?:string,
    objective?: string,
}
