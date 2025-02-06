/*export const login = async (userCredentials: { username: string, password: string }) => {
    try {
      const res = await fetch("/api/login", {
        method: "POST",
        body: JSON.stringify(userCredentials),
        headers: { "Content-Type": "application/json" }
      });
      return res;
    } catch (error) {
      throw error;
    }
  };*/
  
  export const userDetailsSubmit = async (userDetailsCredentials: {
    userId: number,
    preferredName: string,
    availability: string,
    birthday: string | null,
    gender: string,
    restrictions?: string,
    goals?: string,
    comments?: string,
  }) => {
    try {
      const res = await fetch("/api/user-details", {
        method: "POST",
        body: JSON.stringify(userDetailsCredentials),
        headers: { "Content-Type": "application/json" }
      });
      return res;
    } catch (error) {
      throw error;
    }
  };