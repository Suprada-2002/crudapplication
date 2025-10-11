import type { User } from "$lib/types/User.js";
import { apiFetch } from "./client.js";

// Get All users
export async function getAllUsers(): Promise<User[]> {
    return apiFetch<User[]>('/users')
}

// Get user by id
export async function getUserById(id: number): Promise<User> {
    return apiFetch<User>(`/users/${id}`)
}


// create a user
export async function addUser(userBody: Omit<User, 'id'>): Promise<User> {
    return apiFetch<User>('/users/adduser', {
        method: 'POST',
        body: JSON.stringify(userBody)
    })
}

// update a user
export async function updateUser(id: number, user: Partial<User>): Promise<User> {
    return apiFetch<User>(`/users/${id}`, {
        method: 'PUT',
        body: JSON.stringify(user)
    })
}

// delete a user
export async function deleteUser(id: number): Promise<{ message: string}> {
    return apiFetch<{message: string}>(`/users/${id}`, {
        method: 'DELETE'
    })
}