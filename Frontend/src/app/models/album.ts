import { Photo } from "./photo";
export class Album {
    constructor(
        public id?: number,
        public albumName?: string,
        public albumTimestamp?: Date,
        public albumPhotos?: Photo[],
        public userIdJson?: number,
    ) {}
}
