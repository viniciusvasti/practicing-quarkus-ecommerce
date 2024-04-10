"use client";
import {
    Table,
    TableBody,
    TableCell,
    TableFooter,
    TableHead,
    TableHeader,
    TableRow,
  } from "@/components/ui/table"
import { Product, ProductWithCategory } from "./page"
import { useState } from "react";
import { Button } from "@/components/ui/button";

export default function Shopping({ products }: { products: ProductWithCategory[] }) {
    const [cart, setCart] = useState<Product[]>([])
  return (
    <div className="flex flex-col gap-5">
        <h1 className="text-3xl">Cart</h1>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>Category</TableHead>
              <TableHead>Name</TableHead>
              <TableHead className="text-right">Price</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {cart.map((product) => (
              <TableRow key={product.id}>
                <TableCell>{product.category.name}</TableCell>
                <TableCell>{product.name}</TableCell>
                <TableCell className="text-right">${product.price.toFixed(2)}</TableCell>
              </TableRow>
            ))}
          </TableBody>
          <TableFooter>
            <TableRow>
              <TableCell colSpan={2} className="text-right font-medium">Total</TableCell>
              <TableCell className="text-right font-medium">${cart.reduce((acc, product) => acc + product.price, 0).toFixed(2)}</TableCell>
            </TableRow>
          </TableFooter>
        </Table>
        <div className="flex justify-between gap-5">
            <Button variant="outline" onClick={() => setCart([])}>Clear Cart</Button>
            <Button onClick={() => 
                fetch('http://localhost:8080/order', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(cart)
                }).then(() => setCart([]))
            }>Submit Order</Button>
        </div>
        <h1 className="text-3xl">Products</h1>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead className="w-[100px]">Category</TableHead>
              <TableHead>Sku</TableHead>
              <TableHead>Name</TableHead>
              <TableHead>Description</TableHead>
              <TableHead className="text-right">Price</TableHead>
              <TableHead></TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {products.map((product) => (
              <TableRow key={product.id}>
                <TableCell className="font-medium">{product.categoryName}</TableCell>
                <TableCell>{product.sku}</TableCell>
                <TableCell>{product.name}</TableCell>
                <TableCell>{product.description}</TableCell>
                <TableCell className="text-right">${product.price.toFixed(2)}</TableCell>
                <TableCell>
                    <button className="text-blue-500" onClick={
                        () => setCart([...cart, {
                            id: product.id,
                            sku: product.sku,
                            name: product.name,
                            description: product.description,
                            price: product.price,
                            category: {
                                id: 0,
                                name: product.categoryName,
                                products: []
                            }
                        }])
        
                    }>Add</button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
    </div>
  );
}
