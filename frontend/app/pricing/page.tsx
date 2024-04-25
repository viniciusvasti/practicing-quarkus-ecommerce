import PricingTable from "./pricing-table";

export type ProductPricing = {
    id: number;
    sku: string;
    price: number;
}

export default async function PricingPage() {
    const productsPricingResponse = await fetch('http://localhost:8080/products-prices')
    const productsPricing: ProductPricing[] = await productsPricingResponse.json()

  return (
    <main className="w-full flex flex-col items-center gap-5">
        <PricingTable products={productsPricing} />
    </main>
  );
}