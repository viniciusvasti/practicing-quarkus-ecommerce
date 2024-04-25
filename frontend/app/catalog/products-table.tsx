"use client";
import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
  } from "@/components/ui/table"
import { Product } from "./page"
import { Button } from "@/components/ui/button";
import Link from "next/link";
import {
    Dialog,
    DialogClose,
    DialogContent,
    DialogDescription,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
  } from "@/components/ui/dialog";
import CategoryForm from "./category-form";
import { useState } from "react";
  

export default function ProductTable({ products }: { products: Product[] }) {
    const [newCategoryOpen, setNewCategoryOpen] = useState(false);
    const closeNewCategory = () => setNewCategoryOpen(false);
    const openNewCategory = () => setNewCategoryOpen(true);

  return (
    <div className="w-full flex flex-col gap-5">
        <Link href="/"><Button variant="link" className="w-fit">Go back</Button></Link>
        <div className="w-full flex justify-between">
            <h1 className="text-3xl">Products Catalog</h1>
            <span>
                <Dialog open={newCategoryOpen} onOpenChange={(open) => {
                    if (!open) {
                        closeNewCategory();
                    }
                }}>
                    <DialogTrigger asChild onClick={openNewCategory}>
                        <Button>New Category</Button>
                    </DialogTrigger>
                    <DialogContent>
                        <DialogHeader>
                            <DialogTitle>Adding new product category</DialogTitle>
                        </DialogHeader>
                        <CategoryForm onSuccessfulSave={closeNewCategory} />
                    </DialogContent>
                </Dialog>
            </span>
        </div>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>SKU</TableHead>
              <TableHead>Name</TableHead>
              <TableHead>Description</TableHead>
              <TableHead>Category</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {products.map((product) => (
              <TableRow key={product.id}>
                <TableCell>{product.sku}</TableCell>
                <TableCell>{product.name}</TableCell>
                <TableCell>{product.description}</TableCell>
                <TableCell>{product.category.name}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
    </div>
  );
}
