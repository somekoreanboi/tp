package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.DeadlineTask;
import seedu.address.model.task.Description;
import seedu.address.model.task.EventTask;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {

    private EditTaskCommand.EditTaskDescriptor descriptor;
    private Task.Priority priority;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskCommand.EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskCommand.EditTaskDescriptor descriptor) {
        this.descriptor = new EditTaskCommand.EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code task}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskCommand.EditTaskDescriptor();
        descriptor.setTaskName(task.getName());
        descriptor.setTags(task.getTags());

        if (task instanceof EventTask) {
            descriptor.setTaskDate(((EventTask) task).getTaskDate());
        }

        if (task instanceof DeadlineTask) {
            descriptor.setDeadline(((DeadlineTask) task).getDeadline());
        }
    }

    /**
     * Sets the {@code Name} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withName(String name) {
        descriptor.setTaskName(new TaskName(name));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditTaskDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code description} into a {@code Task} and set it to the {@code Task} that we are building.
     */
    public EditTaskDescriptorBuilder withPriority(String description) {
        if (description.contains("H") || description.contains("h")) {
            this.priority = Task.Priority.HIGH;
        } else if (description.contains("M") || description.contains("m")) {
            this.priority = Task.Priority.MEDIUM;
        } else {
            this.priority = Task.Priority.LOW;
        }
        return this;
    }

    public EditTaskCommand.EditTaskDescriptor build() {
        return descriptor;
    }
}
