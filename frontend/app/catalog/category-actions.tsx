"use server";

export async function get() {
    const response = await fetch('http://localhost:8080/products-category')
    return {
        status: response.status,
        body: await response.json()
    }
}

export async function post({ body }: { body: { name: string } }) {
    const { name } = body
    const response = await fetch('http://localhost:8080/products-category', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name })
    })
    return {
        status: response.status,
        body: await response.json()
    }
}