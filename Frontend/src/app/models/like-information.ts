import { User } from "./user";

export class LikeInformation {
    constructor ( 
        public like?: number,
        public users?: User[],
        public likeIds: Set<number> = new Set<number>()
    ) {}
}
