import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { post } from "./category-actions"

export default function CategoryForm({ onSuccessfulSave }: { onSuccessfulSave: () => void }) {
    return (
        <form className="mt-3 flex flex-col gap-3"
            action={async e => {
                const name = e.get('name') as string
                const response = await post({
                    body: {
                        name
                    }
                })
                if (response.status === 201) {
                    onSuccessfulSave()
                }
            }}
        >
            <Label htmlFor="category">
                Category name
            </Label>
            <Input type="text" name="name" id="category" />
            <Button className="w-1/2" type="submit">Save</Button>
        </form>
    )
}