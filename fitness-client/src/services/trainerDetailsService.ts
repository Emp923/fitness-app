export type Trainer = {
    trainerId: number,
    name: string,
    bio: string,
    specialtyOne: string,
    specialtyTwo: string,
    certification: string,
    photograph: string
};

export const getTrainers = async (token: string): Promise<Trainer[]> => {
    const res = await fetch("/api/trainers", {
        method: "GET",
        headers: {"Authorization": `Bearer ${token}`}
    });
    return res.json();
};
