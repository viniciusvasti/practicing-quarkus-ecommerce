import dns from 'node:dns';
import Shopping from './shopping';
import { Button } from '@/components/ui/button';
import Link from 'next/link';
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
    <main className="flex flex-col items-center gap-5">
        <div className='flex gap-5'>
            <Link href="/catalog">
                <Button>Catalog</Button>
            </Link>
            <Link href="inventory">
                <Button>Inventory</Button>
            </Link>
            <Link href="pricing">
                <Button>Pricing</Button>
            </Link>
        </div>
      <Shopping products={products} />
    </main>
  );
}
