import InventoryTable from "./inventory-table";

export type ProductInventory = {
    id: number;
    sku: string;
    stockUnits: number;
}

export default async function PricingPage() {
    const productsInventoryResponse = await fetch('http://localhost:8080/products-inventory')
    const productsInventory: ProductInventory[] = await productsInventoryResponse.json()

  return (
    <main className="w-full flex flex-col items-center gap-5">
        <InventoryTable products={productsInventory} />
    </main>
  );
}