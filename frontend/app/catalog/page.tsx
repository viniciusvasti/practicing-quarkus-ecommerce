import ProductTable from "./products-table";

export type ProductCategory = {
    id: number;
    name: string;
};

export type Product = {
    id: number;
    sku: string;
    name: string;
    description: string;
    category: ProductCategory;
}

export default async function PricingPage() {
    const productsResponse = await fetch('http://localhost:8080/products')
    const products: Product[] = await productsResponse.json()

  return (
    <main className="w-full flex flex-col items-center gap-5">
        <ProductTable products={products} />
    </main>
  );
}