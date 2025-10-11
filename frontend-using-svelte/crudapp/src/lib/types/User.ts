// export interface User {
//   id: number
//   userName: string
//   email: string
//   phoneNo: string
// }

export interface User {
  id?: number;
  userName: string;
  email: string;
  phoneNo: string;
  password?: string;
}

export interface AuthRequest {
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
}
