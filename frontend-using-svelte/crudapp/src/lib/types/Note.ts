import type { User } from './User.js'

export interface Note {
  id: number
  title: string
  description: string
  content: string
  createdAt: string
  user: User
}
