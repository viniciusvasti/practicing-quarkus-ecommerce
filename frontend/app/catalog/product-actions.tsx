"use server";

import { Product } from "./page";

export async function get(): Promise<{
    status: number;
    body: Product[];
}> {
    const response = await fetch('http://localhost:8080/products')
    return {
        status: response.status,
        body: await response.json()
    }
}

export async function post({ body }: { body: {
    sku: string;
    name: string;
    description: string;
    categoryId: number;
} }): Promise<{
    status: number;
    body: Product;
}> {
    const { sku, name, description, categoryId } = body
    const response = await fetch('http://localhost:8080/products', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            sku,
            name,
            description,
            categoryId
        })
    })
    return {
        status: response.status,
        body: await response.json()
    }
}