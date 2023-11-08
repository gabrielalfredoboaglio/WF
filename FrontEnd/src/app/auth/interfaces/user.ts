import { Role } from './role';
import { Sex } from './sex';

export interface User {
  id: number;
  name: string;
  email: string;
  password: string;
  clientSince?: Date;
  roles?: Role;
  profileImg?: null | File;
  weight?: number | null;
  height?: number | null;
  sex?: Sex;
  age?: number;
  token?: string;
}
