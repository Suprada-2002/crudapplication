// Reusable type safe fetch helper

const API_BASE = 'http://localhost:8080/api'

export interface ApiError{
    status: number
    message: string
}

export async function apiFetch<T>(
    endpoint: string,
    options: RequestInit = {}
): Promise<T> {
    try{
        const res = await fetch(`${API_BASE}${endpoint}`, {
            headers: {
        'Content-Type': 'application/json',
        ...(options.headers || {})
      },
      ...options
        })

         // Check if response failed
    if (!res.ok) {
      const message = await res.text()
      throw {
        status: res.status,
        message: message || res.statusText
      } as ApiError
    }

    // Handle empty response bodies
    if (res.status === 204) return {} as T

    const data = (await res.json()) as T
    return data

    }catch(err: unknown){
        
        console.error('API fetch error:', err)
        if (typeof err === 'object' && err !== null && 'status' in err)
        throw err as ApiError
        throw { status: 500, message: 'Network or server error' } as ApiError
    }
}