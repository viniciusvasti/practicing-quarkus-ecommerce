import dns from 'node:dns';
import Shopping from './shopping';
dns.setDefaultResultOrder('ipv4first');

export type Product = {
    id: number;
    sku: string;
    name: string;
    description: string;
    price: number;
    category: ProductCategory;
};

export type ProductCategory = {
    id: number;
    name: string;
    products: Product[];
};

export type ProductWithCategory = {
    id: number;
    sku: string;
    name: string;
    description: string;
    price: number;
    categoryName: string;
};

export default async function Home() {
    const productsCatalogResponse = await fetch('http://localhost:8080/store-products')
    const productsCatalog: ProductCategory[] = await productsCatalogResponse.json()
    const products: ProductWithCategory[] = productsCatalog.flatMap((category) => {
        return category.products.map((product) => ({
            id: product.id,
            sku: product.sku,
            name: product.name,
            description: product.description,
            price: product.price,
            categoryName: category.name,
        }))
    })

  return (
    <main className="flex min-h-screen flex-col items-center gap-5 p-24">
      <Shopping products={products} />
    </main>
  );
}
