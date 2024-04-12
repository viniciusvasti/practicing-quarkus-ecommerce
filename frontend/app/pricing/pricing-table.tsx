"use client";
import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
  } from "@/components/ui/table"
import { ProductPricing } from "./page"
import { Button } from "@/components/ui/button";
import Link from "next/link";

export default function PricingTable({ products }: { products: ProductPricing[] }) {
  return (
    <div className="w-full flex flex-col gap-5">
        <Link href="/"><Button variant="link" className="w-fit">Go back</Button></Link>
        <h1 className="text-3xl">Products Prices</h1>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>SKU</TableHead>
              <TableHead className="text-right">Price</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {products.map((product) => (
              <TableRow key={product.id}>
                <TableCell>{product.sku}</TableCell>
                <TableCell className="text-right">${product.price.toFixed(2)}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
    </div>
  );
}
