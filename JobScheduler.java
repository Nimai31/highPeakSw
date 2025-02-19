import java.util.*;

class Job {
    int start, end, profit;

    public Job(int start, int end, int profit) {
        this.start = start;
        this.end = end;
        this.profit = profit;
    }
}

public class JobScheduler {
    static ArrayList<Integer> jobSequencing(List<Job> jobs) {
        int n = jobs.size();
        ArrayList<Integer> ans = new ArrayList<>(Arrays.asList(0, 0)); // [Remaining Jobs, Remaining Earnings]

        // Sort jobs by end time
        jobs.sort((a, b) -> a.end - b.end);

        List<Job> selectedJobs = new ArrayList<>();
        int totalEarningsLokesh = 0, lastEndTime = 0;

        for (Job job : jobs) {
            if (job.start >= lastEndTime) { // Ensure non-overlapping jobs
                selectedJobs.add(job);
                totalEarningsLokesh += job.profit;
                lastEndTime = job.end;
            }
        }

        // Calculate remaining jobs and earnings
        int remainingJobs = n - selectedJobs.size();
        int totalEarningsAll = jobs.stream().mapToInt(j -> j.profit).sum();
        int remainingEarnings = totalEarningsAll - totalEarningsLokesh;

        ans.set(0, remainingJobs);
        ans.set(1, remainingEarnings);

        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of Jobs:");
        int n = scanner.nextInt();
        scanner.nextLine();
        List<Job> jobs = new ArrayList<>();

        System.out.println("Enter job start time, end time, and earnings:");
        for (int i = 0; i < n; i++) {
            int start = Integer.parseInt(scanner.nextLine().trim());
            int end = Integer.parseInt(scanner.nextLine().trim());
            int profit = Integer.parseInt(scanner.nextLine().trim());
            jobs.add(new Job(start, end, profit));
        }

        ArrayList<Integer> ans = jobSequencing(jobs);
        System.out.println("The number of tasks and earnings available for others");
        System.out.println("Task: " + ans.get(0));
        System.out.println("Earnings: " + ans.get(1));

        scanner.close();
    }
}