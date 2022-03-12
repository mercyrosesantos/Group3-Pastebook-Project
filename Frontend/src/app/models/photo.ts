import { User } from "./user";

export class Photo {
    constructor(
        public id?: number,
        public caption?: string,
        public photoTimestamp?: Date,
        public isActive?: boolean,
        public userId?: number,
        public user?: User,
        public image?: string
    ) {}
}
